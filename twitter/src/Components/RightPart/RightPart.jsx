import React from 'react'
import SearchIcon from '@mui/icons-material/Search';
import BrightnessHighIcon from '@mui/icons-material/BrightnessHigh';
import { Button } from '@mui/material';
import { MoreHoriz } from '@mui/icons-material';

const RightPart = () => {
    const handleChangeTheme=()=>{
        console.log("handle change theme");
    }
  return (
    <div>
    <div className='relative flex items-center'>

        <input type="text" className='py-2 rounded-full text-grey-500 w-full pl-12' />

        <div className='absolute top-0 left-0 pl-3 pt-3'>
            <SearchIcon className='text-grey-500'/>

        </div>
        <BrightnessHighIcon className=" ml-3 cursor-pointer" onclick={handleChangeTheme}/>
    </div>
    <section className="my-5 ">
        <h1 className="text-xl font-bold"> Get Verified</h1>
        <h1 className="font-bold my-2">Subscribe to unlook new Features </h1>
        <Button variant='contained' sx={{padding:"10px", paddingX:"20px", borderRadius:"25px"}}>
            Get Verified
        </Button>

    </section>
    <section classname="mt-7 space-y-5">
        <h1 className="font-bold text-xl py-1">What's happening!</h1>
        <div>
            <p classsName="text-sm">
                IPL men's  · LIVE
                {/*Entertainment - Trending*/}

            <p className="font-bold">
            CSK VS MI 
            </p>
            { [1,1,1,1].map((item)=> <div className="flex justify-between w-full">
                <div>
                    <p>
                    Entertainment  · Trending
                    </p>
                    <p className='font-bold'>#TheMarvels</p>
                    <p>56.8k Tweets</p>
                </div>
                <MoreHoriz/>
            </div> )}
            </p>
        </div>

    </section>
    </div>
  )
}

export default RightPart