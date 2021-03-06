package com.realme.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

// Listner Class that performs certain actions based on certain actions
public class TestListners implements ITestListener {
	
	// Listens to start of test event
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test Started:"+result.getInstanceName());
		
	}
	
	// Listens to passing of tests
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test Passed:"+result.getInstanceName());
	}
	
	// Listens to failure of tests
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test Failed:"+result.getInstanceName());
	}
	
	// Listens to skipping of test case
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test Skipped:"+result.getInstanceName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	// Listens to finishing of test
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Test completed:"+context.getName());
	}

}
