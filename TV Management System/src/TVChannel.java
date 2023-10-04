public abstract class TVChannel
{
	String channelName;
	String language;
	String catagory;
	int price;
	
	public TVChannel(String channelName, String language, String catagory, int price)
	{
		this.channelName = channelName;
		this.language = language;
		this.catagory = catagory;
		this.price = price;
	}
	
	
	public String getChannelName()
	{
		return channelName;
	}


	public void setChannelName(String channelName)
	{
		this.channelName = channelName;
	}


	public String getLanguage()
	{
		return language;
	}


	public void setLanguage(String language)
	{
		this.language = language;
	}


	public String getCatagory()
	{
		return catagory;
	}


	public void setCatagory(String catagory)
	{
		this.catagory = catagory;
	}


	public int getPrice()
	{
		return price;
	}


	public void setPrice(int price)
	{
		this.price = price;
	}

}
