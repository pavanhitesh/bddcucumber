package com.singtel.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.singtel.base.BaseWebDriver;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class ScreenShotUtil {

    public static void takeScreenshot(){
        TakesScreenshot scrShot =((TakesScreenshot)BaseWebDriver.driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(BaseWebDriver.baseLocation+"//screenshots//"+getTimeStamp()+".jpg");
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        }catch (IOException e){
            System.out.println("Failed to capture Screenshot");
        }
    }

    public static long getTimeStamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

}
