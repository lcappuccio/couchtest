package org.systemexception.couchtest.pojo;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author leo
 * @date 24/10/15 22:43
 */
public class Data {

	private final String dataUUID;
	private final InputStream inputStream;

	public Data() {
		this.dataUUID = UUID.randomUUID().toString();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		inputStream = classloader.getResourceAsStream("image.jpg");
	}

	public String getDataUUID() {
		return dataUUID;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Data data = (Data) o;

		if (dataUUID != null ? !dataUUID.equals(data.dataUUID) : data.dataUUID != null) return false;
		return !(inputStream != null ? !inputStream.equals(data.inputStream) : data.inputStream != null);

	}

	@Override
	public int hashCode() {
		int result = dataUUID != null ? dataUUID.hashCode() : 0;
		result = 31 * result + (inputStream != null ? inputStream.hashCode() : 0);
		return result;
	}
}
