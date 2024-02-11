import React,{useRef, useState} from "react";


import img1 from "../../assets/img/pexels-markus-spiske-1343537.jpg";
import styles from './Form.module.css';
import { Link } from "react-router-dom";
import axiosClient from "../../axios-client";
import { useStateContext } from "../../contexts/ContextProvider";
import { Navigate } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';



const Form = () => {
    const nameRef = useRef();
   // const [errors, setErrors] = useState(null);
    const navigate = useNavigate();


    const onSubmit = (ev) =>{
        ev.preventDefault()
        const payload ={
            name: nameRef.current.value
        }

        axiosClient.post('/category/add', payload)
        .then(({data})=>{
            console.log(data)
             navigate('/admin/categorie')
        })
    .catch(err => {
        const response = err.response;
        if(response && response.status == 422){
            setErrors(response.data.errors)
        }
    })

}
  return (
    <section className={styles.FormFor}>
        <div className={styles.register}>
         <div className={styles.col1}>
            <h2>Add New Product</h2>
          
            <form id={styles.form345} className={`${styles.flex34} ${styles.flexcol7}`} >
             <input ref={nameRef} type="text" placeholder="name" />

             <button onClick={onSubmit} className={styles.btn}>Add new Product</button>
            </form>
         </div>
         <div className={styles.col2}>
            <img src={img1} alt="a header image" />
         </div>
        </div>
    </section>
  );
};

export default Form; // Exporting the corrected Card component
