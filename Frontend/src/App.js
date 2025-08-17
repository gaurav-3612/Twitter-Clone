import { Route, Routes } from 'react-router-dom';
import './App.css';
import Authentication from './Components/Authentication/Authentication';
import HomePage from './Components/HomePage/HomePage';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect, useState } from 'react';
import { getUserProfile } from './Store/Auth/Action';

import darkTheme from './Theme/DarkTheme';
import lightTheme from './Theme/LightTheme';
import { Box, Button, CssBaseline } from '@mui/material';
import { Palette } from '@mui/icons-material';

function App() {
  const dispatch=useDispatch();
  const {auth}=useSelector(store=>store);
  const jwt = localStorage.getItem("jwt")
  

  useEffect(()=>{

    if(jwt){
      dispatch(getUserProfile(jwt))
    }
  
  },[auth.jwt,jwt])

    return (
      <Box sx={{}}>
        {/* <Button variant='content' color='success'>Check Theme</Button> */}
          <Routes>
        <Route path='/*' element={ auth.user?.fullName? <HomePage/>:<Authentication/>}></Route>
        <Route path='/signin' element={<Authentication/>}></Route>
        <Route path='/signup' element={<Authentication/>}></Route>
        {/* <Route path='/profile' element={<HomePage/>}></Route> */}
      </Routes>
      </Box>
    
      
  );
}

export default App;
