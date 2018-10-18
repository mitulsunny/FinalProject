package com.bit.obaidulla.lastProject.common;

public class ResourcesHelper {
public static String getResourcesPath(String path) {
	String basePath=System.getProperty("user.dir");
	return basePath+path;
}
}
