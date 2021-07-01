package com.gotcha.www.user.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gotcha.www.workList.vo.WorkListVO;



public class RequestBodyWrapper extends HttpServletRequestWrapper {
	
	private static final Logger log = LoggerFactory.getLogger(RequestBodyWrapper.class);
	
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
	private static final int EOF = -1;
	
	private final Charset encoding;
	private byte[] rawData;
	
	public RequestBodyWrapper(HttpServletRequest request) {
		
		super(request);
		String encoding = request.getCharacterEncoding();
		this.encoding = StringUtils.isEmpty(encoding) ? StandardCharsets.UTF_8 : Charset.forName(encoding);
		try {
			InputStream is = request.getInputStream();
			this.rawData = toByteArray(is);
			
		} catch(IOException e) {
			log.error("Stream열다가 IOException 발생", e);
		}
//		try {
//			ObjectMapper objectMapper = new ObjectMapper();
//			WorkListVO workListVO = objectMapper.readValue(request.getInputStream(), WorkListVO.class);
//			System.out.println("work : " + workListVO);
//		} catch(Exception e) {
//			
//		}
	}

	// InputStream 받아서 byte[] 반환하는 유틸성 메소드
	private byte[] toByteArray(final InputStream input) throws IOException {
		final ByteArrayOutputStream output = new ByteArrayOutputStream();
		copyLarge(input, output, new byte[DEFAULT_BUFFER_SIZE]);
		return output.toByteArray();
	}
	
	private void copyLarge(final InputStream input, final OutputStream output,
						final byte[] buffer) throws IOException {
		int n;
		while(EOF != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		
	}


	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = 
				new ByteArrayInputStream(this.rawData);
		
		return new ServletInputStream() {

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener listener) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}
			
		};
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream(), this.encoding));
	}
	
}
