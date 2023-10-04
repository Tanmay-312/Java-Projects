public class MoviesChannel extends TVChannel
{
	int additionalFee = 150;
	public MoviesChannel(String channelName, String language, String catagory, int price)
	{
		super(channelName, language, catagory, price);
	}
	
	@Override
	public int getPrice()
	{
		return super.getPrice() + additionalFee;
	}

}