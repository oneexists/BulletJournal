package com.bujo.data.factories;

public class LogProvider {
	public static LogFactory getFactory() {
		return new LogFactoryImpl();
	}
}
