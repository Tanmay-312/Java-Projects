public class DocumentaryChannel extends TVChannel
{
	int additionalFee = 120;
	public DocumentaryChannel(String channelName, String language, String catagory, int price)
	{
		super(channelName, language, catagory, price);
	}
	
	@Override
	public int getPrice()
	{
		return super.getPrice() + additionalFee;
	}

}