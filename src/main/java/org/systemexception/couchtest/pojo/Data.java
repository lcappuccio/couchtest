package org.systemexception.couchtest.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.InputStream;

/**
 * @author leo
 * @date 24/10/15 22:43
 */
public class Data {

	private int dataCounter;
	@SerializedName("_id")
	private String dataId;
	@SerializedName("_rev")
	private String dataRev;
	private final InputStream inputStream;

	public Data() {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		inputStream = classloader.getResourceAsStream("image.jpg");
	}

	public int getDataCounter() {
		return dataCounter;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setDataCounter(int dataCounter) {
		this.dataCounter = dataCounter;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getDataRev() {
		return dataRev;
	}

	public void setDataRev(String dataRev) {
		this.dataRev = dataRev;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Data data = (Data) o;

		if (dataCounter != data.dataCounter) return false;
		if (dataId != null ? !dataId.equals(data.dataId) : data.dataId != null) return false;
		if (dataRev != null ? !dataRev.equals(data.dataRev) : data.dataRev != null) return false;
		return !(inputStream != null ? !inputStream.equals(data.inputStream) : data.inputStream != null);

	}

	@Override
	public int hashCode() {
		int result = dataCounter;
		result = 31 * result + (dataId != null ? dataId.hashCode() : 0);
		result = 31 * result + (dataRev != null ? dataRev.hashCode() : 0);
		result = 31 * result + (inputStream != null ? inputStream.hashCode() : 0);
		return result;
	}
}
