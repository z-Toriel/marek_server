package com.marek.controller;

import com.marek.common.BaseController;
import com.marek.common.R;
import com.marek.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Slf4j
@CrossOrigin
@RestController
public class FileUpload extends BaseController {
    //上传图片的方法
    @PostMapping("/upload")
    public R uploadFile(@RequestBody MultipartFile file, HttpServletRequest request) throws Exception{
        File targetFile=null;
        //上传图片成功返回的图片的路径
        String url = "";
        try {
            //获得文件名加后缀
            String filename = file.getOriginalFilename();
            if(filename!=null && filename !=""){
                //图片访问的URI
                String returnURL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/upload/imgs/movie";
                //文件存储的位置
                String path = request.getSession().getServletContext().getRealPath("") + "upload" + File.separator + "imgs"+File.separator+"movie";

                //文件后缀
                String fileSuffix = filename.substring(filename.lastIndexOf("."));
                //新的文件名
                filename = System.currentTimeMillis()+"_"+new Random().nextInt(1000)+fileSuffix;

                //先判断文件是否存在
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String fileAdd = sdf.format(new Date());
                //获取文件夹路径
                path = path+File.separator+fileAdd+File.separator;
                System.out.println(path);
                File directory = new File(path);
                //如果文件夹不存在就创建
                if(!directory.exists()&&!directory.isDirectory()){
                    directory.mkdirs();
                }
                //将图片存入文件夹
                targetFile  = new File(directory,filename);
                //将上传的文件写到服务器上指定的文件夹
                file.transferTo(targetFile);
                String projectPath = System.getProperty("user.dir");
                System.out.println("projectPath路径"+projectPath);
                //文件复制
                String src = path+filename;
                String destDir = projectPath + File.separator +"src"+File.separator+"main"+ File.separator +"resources"+File.separator+"static"+ File.separator+"upload"+File.separator+"imgs" + File.separator +"movie"+File.separator+ fileAdd + File.separator;
                copyFile(src,destDir,filename);

                url = returnURL +"/"+ fileAdd + "/"+filename;
                return R.ok().data("url",url);
            }
            return R.error(ResultCode.DATA_IS_WRONG,"图片上传失败");
        }catch(IOException e){
            e.printStackTrace();
            return R.error(ResultCode.DATA_IS_WRONG,"图片上传失败");
        }
    }

    /**
     * 文件复制
     * @param src
     * @param destDir
     * @param fileName
     * @throws IOException
     */
    public void copyFile(String src,String destDir,String fileName) throws IOException{
        FileInputStream in=new FileInputStream(src);
        File fileDir = new File(destDir);
        if(!fileDir.isDirectory()){
            fileDir.mkdirs();
        }
        File file = new File(fileDir,fileName);

        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream out=new FileOutputStream(file);
        int c;
        byte buffer[]=new byte[1024];
        while((c=in.read(buffer))!=-1){
            for(int i=0;i<c;i++){
                out.write(buffer[i]);
            }

        }
        in.close();
        out.close();
    }
}
