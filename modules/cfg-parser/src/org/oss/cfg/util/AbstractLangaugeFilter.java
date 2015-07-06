package org.oss.cfg.util;

import java.io.File;
import java.io.FileFilter;

public abstract class AbstractLangaugeFilter implements FileFilter{

	protected String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}
}
