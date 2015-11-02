package org.systemexception.couchtest.pojo;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * $Id$
 *
 * @author lcappuccio
 * @date 2/11/15 12:17
 * Copyright (C) 2015 Scytl Secure Electronic Voting SA
 * All rights reserved.
 */
public class TallySheet {

	private final int tallySheetSequence;
	private final long tallySheetTimestamp;
	private final String tallySheetBase64String;

	public TallySheet (final int tallySheetSequence, final String tallySheetFileName) {
		this.tallySheetSequence = tallySheetSequence;
		this.tallySheetTimestamp = System.currentTimeMillis();
		this.tallySheetBase64String = encodeFileToBase64Binary(tallySheetFileName);
	}

	public int getTallySheetSequence() {
		return tallySheetSequence;
	}

	public long getTallySheetTimestamp() {
		return tallySheetTimestamp;
	}

	public String getTallySheetBase64String() {
		return tallySheetBase64String;
	}

	private String encodeFileToBase64Binary(String fileName) {

		File file = new File(fileName);
		byte[] bytes = new byte[0];
		try {
			bytes = loadFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] encoded = Base64.encodeBase64(bytes);
		String encodedString = new String(encoded);

		return encodedString;
	}

	private byte[] loadFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		long length = file.length();
		if (length > Integer.MAX_VALUE) {
			throw new UnsupportedOperationException("File too large");
		}
		byte[] bytes = new byte[(int)length];

		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
			offset += numRead;
		}

		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "+file.getName());
		}

		is.close();
		return bytes;
	}

}
