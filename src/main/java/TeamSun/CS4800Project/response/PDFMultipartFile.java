package TeamSun.CS4800Project.response;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

//Thanks to https://stackoverflow.com/questions/18381928/how-to-convert-byte-array-to-multipartfile
public class PDFMultipartFile implements MultipartFile {

	private final byte[] file;
	private final String type;

	public PDFMultipartFile(byte[] file, String type) {
		this.file = file;
		this.type = type;
	}

	@Override
	public String getName() {
		return "testname";
	}

	@Override
	public String getOriginalFilename() {
		return null;
	}

	@Override
	public String getContentType() {
		return type;
	}

	@Override
	public boolean isEmpty() {
		return file == null || file.length == 0;
	}

	@Override
	public long getSize() {
		return file.length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return file;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(file);
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		FileOutputStream stream = new FileOutputStream(dest);
		stream.write(file);
		stream.close();
	}

}
