import React from "react";
import './NavbarFor.css';
import { Link, useNavigate } from "react-router-dom";
// import { FcAddressBook } from "react-icons/fc";
import axiosClient from "../../axios-client";
import { useStateContext } from "../../contexts/ContextProvider.jsx";

const NavBarFor = () => {
const {user, token} = useStateContext()
const navigate = useNavigate();
const {setUser, setToken} = useStateContext();

const logout = (ev) =>{
    /* ev.preventDefault()
    const payload ={

        user_id:user.id,


    }
    axiosClient.post('/logout', payload)
    .then(({data})=>{ */
         setUser({})
         setToken(null)
        localStorage.removeItem('ACCESS_TOKEN')
        navigate('/login')
        console.log(data);



 /*    })
.catch(err => {
    const response = err.response;
    if(response && response.status == 422){
        console.log(response.data.errors);
    }
})
 */

}

  return (
<>
  

  <div className="header">


  <a href="#" className='logo'>Ecommere System </a>
  <ul className='main-nav'>
  {!token && (
                <li><Link to="/login" >login</Link></li>

             )}
              {!token && (
                <li><Link to="/singup" >sign_up</Link></li>

             )}
                {token && (
                <li><Link to="/carts" >carts</Link></li>
               )}
               {token && (
                <li><Link to="/login"  onClick={logout}>log out</Link></li>
               )}

{!token && (    <li ><Link to="/admin/login" >login_admin</Link></li>
  )}
    
  </ul>
</div>

  </>
  );
};

export default NavBarFor                  ; // Exporting the corrected Card component
