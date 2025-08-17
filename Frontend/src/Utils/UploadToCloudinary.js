export const uploadToCloudinary = async (pics,fileType) => {
    if (pics) {
      
      const data = new FormData();
      data.append("file", pics);
      data.append("upload_preset", "qyxwhoyn");
      data.append("cloud_name", "dd3n7w5ut");
  
      const res = await fetch(`https://api.cloudinary.com/v1_1/dd3n7w5ut/image/upload`, {
        method: "post",
        body: data,
      })
        
        const fileData=await res.json();
        console.log("url : ", fileData.url.toString());
        return fileData.url.toString();
  
    } else {
      console.log("error");
    }
  };