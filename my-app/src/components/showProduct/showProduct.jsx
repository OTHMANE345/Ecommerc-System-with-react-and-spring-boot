import React from 'react'
import img1 from "../../assets/img/pexels-markus-spiske-1343537.jpg";
import './showProduct.css';
import {  useNavigate, useParams } from 'react-router-dom';
import { Form } from 'react-bootstrap';

import axiosClient from "../../axios-client";
import {useEffect, useState} from "react";
import { useRef } from 'react';
import { useStateContext } from '../../contexts/ContextProvider'

export default function showProduct() {
const {id}= useParams();
const quantityRef = useRef();
const [errors, setErrors] = useState(null);

 const {user} = useStateContext();
const userId = user ? user.id : null;
const [products, setProducts] = useState([]);
const navigate = useNavigate();

useEffect( () => {
   getP()
} , []);


const getP = () => {
    const payload ={
        id: id,
    }
    console.log(id);

   axiosClient.post('/product/show', payload)
   .then(({data})=>{
       setProducts(data)
       console.log(data)
       //  setNavig(true);

   })
.catch(err => {
   const response = err.response;
   if(response && response.status == 422){
       console.log(response.data.errors);
   }
})
}

const onSubmit = (ev) =>{
    ev.preventDefault()
    const payload ={
        price:products.price,
        product_id:products.id,
        product_name:products.name,
        user_id:user.id,
        quantity:quantityRef.current.value,
        image:products.image,

    }
    console.log(payload);
    axiosClient.post('/cart/add', payload)
    .then(({data})=>{
        console.log(data);

navigate('/carts')

    })
.catch(err => {
    const response = err.response;
    if(response && response.status == 422){
        console.log(response.data.errors);
    }
})


}

    return (
    <div className='is-showProduct'>
            <div className='is-wrapper'>

            <img src={`data:image/jpeg;base64,${products.image}`} alt="" />
        <div className="is-text-box">
        
                <h2>{products.name}</h2>
                <h3>{products.price} $</h3>
                <p>{products.categoryName}</p>

                <p>{products.description}</p>
                <form  onSubmit={onSubmit}> 
            <input  ref={quantityRef}  type="number" className="form-control"   placeholder="Quantity" />

             <button className="btn btn-primary my-3" >Add to Cart</button>

            </form>
          </div>

          </div>

    </div>
  )
}