package com.test;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utility.ExtentReportsClass;
import utility.ScreenShot;

public class Listeners extends  ExtentReportsClass implements ITestListener {


	ExtentReports report = ExtentReportsClass.createExtentReports();
	static ExtentTest test ;
	static ThreadLocal<ExtentTest> threadTest = new ThreadLocal<ExtentTest>();
    public void onFinish(ITestContext result) {					
        // TODO Auto-generated method stub				
        		report.flush();
    }			
    public void onStart(ITestContext result) {					
        // TODO Auto-generated method stub				
        		
    }		
	
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {					
        // TODO Auto-generated method stub				
        		
    }		
	
    public void onTestFailure(ITestResult result) {					
        // TODO Auto-generated method stub
    	try {
    		threadTest.get().info(result.getMethod().getMethodName() + " Failed");
    		threadTest.get().fail(result.getMethod().getMethodName() + " Failed");
    		threadTest.get().fail(result.getThrowable());
    		threadTest.get().log(Status.FAIL, "Failed");
    		System.out.println("Test case " + result.getMethod().getMethodName() + " Failed, screenshot taken");
			String path = takeScreenshotAtFailure(result.getMethod().getMethodName());
			threadTest.get().addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        		
    }		
	
    public void onTestSkipped(ITestResult result) {					
        // TODO Auto-generated method stub				
        		
    }			
    public void onTestStart(ITestResult result) {	
    	
		test  = report.createTest(result.getMethod().getMethodName());
		threadTest.set(test);
		threadTest.get().info("Test Started");
        // TODO Auto-generated method stub	
    	System.out.println("Test case " + result.getMethod().getMethodName() + " statred" );
        		
    }		
	
    public void onTestSuccess(ITestResult result) {		
    	
    	threadTest.get().info("Test Passed");
    	threadTest.get().log(Status.PASS, result.getName()+" PASSEDs");
        // TODO Auto-generated method stub	
    	
        		
    }
}

