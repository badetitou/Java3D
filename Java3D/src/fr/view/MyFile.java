package fr.view;

import java.io.File;

public class MyFile extends File {

	/**
	 * @param arg0
	 */
	public MyFile(String arg0) {
		super(arg0);
	}

	@Override
	public String toString() {
		return getName();
	}
}