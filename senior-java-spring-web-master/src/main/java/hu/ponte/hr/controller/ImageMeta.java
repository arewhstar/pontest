package hu.ponte.hr.controller;

import lombok.Builder;
import lombok.Getter;

/**
 * @author zoltan
 */
@Getter
@Builder
public class ImageMeta
{
	private String id;
	private String name;
	private String mimeType;
	private long size;
	private String digitalSign;
	private byte[] image;

	public ImageMeta(String id,String name,String mimeType,long size,String digitalSign, byte[] image){
		this.id=id;
		this.name = name;
		this.mimeType = mimeType;
		this.size = size;
		this.digitalSign= digitalSign;
		this.image = image;
	}
	public byte[] getBytes(){
		return image;
	}
}

