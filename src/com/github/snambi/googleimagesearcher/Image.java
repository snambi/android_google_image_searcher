package com.github.snambi.googleimagesearcher;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {
	/*
	"GsearchResultClass": "GimageSearch",
   "width": "450",
   "height": "450",
   "imageId": "Yt3TRC1vxzhazM",
   "tbWidth": "127",
   "tbHeight": "127",
   "unescapedUrl": "http://www.touchnote.com/files/assets/STAN009.jpg",
   "url": "http://www.touchnote.com/files/assets/STAN009.jpg",
   "visibleUrl": "www.touchnote.com",
   "title": "Touchnote - Personalised \u003cb\u003eFuzzy Monkey\u003c/b\u003e greeting cards design by Dan \u003cb\u003e...\u003c/b\u003e",
   "titleNoFormatting": "Touchnote - Personalised Fuzzy Monkey greeting cards design by Dan ...",
   "originalContextUrl": "http://www.touchnote.com/photo/card-design/Fuzzy+Monkey",
   "content": "Card Design \u003cb\u003eFuzzy Monkey\u003c/b\u003e",
   "contentNoFormatting": "Card Design Fuzzy Monkey",
   "tbUrl": "http://images.google.com/images?q\u003dtbn:Yt3TRC1vxzhazM:www.touchnote.com/files/assets/STAN009.jpg" 
	 */
	
	private int width;
	private int height;
	private String id;
	private String unescapedUrl;
	private String url;
	private String visibleUrl;
	
	private String title;
	private String titleNoFormatting;
	private String content;
	private String contentNoFormatting;
	
	
	private int thumbHeight;
	private int thumbWidth;
	private String thumbUrl;
	
	public Image(){
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnescapedUrl() {
		return unescapedUrl;
	}
	public void setUnescapedUrl(String unescapedUrl) {
		this.unescapedUrl = unescapedUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String escapedUrl) {
		this.url = escapedUrl;
	}
	public String getVisibleUrl() {
		return visibleUrl;
	}
	public void setVisibleUrl(String visibleUrl) {
		this.visibleUrl = visibleUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleNoFormatting() {
		return titleNoFormatting;
	}
	public void setTitleNoFormatting(String titleNoFormatting) {
		this.titleNoFormatting = titleNoFormatting;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentNoFormatting() {
		return contentNoFormatting;
	}
	public void setContentNoFormatting(String contentNoFormatting) {
		this.contentNoFormatting = contentNoFormatting;
	}
	public int getThumbHeight() {
		return thumbHeight;
	}
	public void setThumbHeight(int thumbHeight) {
		this.thumbHeight = thumbHeight;
	}
	public int getThumbWidth() {
		return thumbWidth;
	}
	public void setThumbWidth(int thumbWidth) {
		this.thumbWidth = thumbWidth;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt( getWidth() );
		dest.writeInt( getHeight() );
		dest.writeString( getId() );
		dest.writeString( getUnescapedUrl());
		dest.writeString( getUrl() );
		dest.writeString( getVisibleUrl() );
		
		dest.writeString( getTitle() );
		dest.writeString( getTitleNoFormatting() );
		dest.writeString( getContent() );
		dest.writeString( getContentNoFormatting() );
		
		dest.writeInt( getThumbHeight() );
		dest.writeInt( getThumbWidth() );
		dest.writeString( getThumbUrl() );
	}
	
	public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {

		@Override
		public Image createFromParcel(Parcel source) {
			return new Image(source);
		}

		@Override
		public Image[] newArray(int size) {
			return new Image[size];
		}
		
	};

	private Image(Parcel in) {
		
		setWidth(in.readInt() );
		setHeight( in.readInt() );
		setId( in.readString() );
		setUnescapedUrl( in.readString());
		setUrl( in.readString() );
		setVisibleUrl( in.readString() );
		
		setTitle( in.readString() );
		setTitleNoFormatting( in.readString() );
		setContent( in.readString() );
		setContentNoFormatting( in.readString() );
		
		setThumbHeight( in.readInt() );
		setThumbWidth( in.readInt() );
		setThumbUrl( in.readString() );
	}
}
