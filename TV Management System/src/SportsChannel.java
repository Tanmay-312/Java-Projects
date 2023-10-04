public class SportsChannel extends TVChannel
{
	int additionalFee = 100;
	public SportsChannel(String channelName, String language, String catagory, int price)
	{
		super(channelName, language, catagory, price);
	}
	
	@Override
	public int getPrice()
	{
		return super.getPrice() + additionalFee;
	}

}
