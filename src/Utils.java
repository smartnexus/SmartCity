import java.util.Random;

public class Utils {
	
	
	/**
	 * @param min -> Minimum value to inc/dec
	 * @param max -> Maximum value to inc/dec
	 * @param value -> Current value
	 * @param limit -> Maximum value operation can reach
	 * @param onlyIncrement -> True if operation should only increment
	 * @return new value
	 */
	public Integer randomIncrement(int min, int max, int value, int limit, boolean onlyIncrement)  {
		Random random = new Random();
    	int diff = (random.nextInt((max - min) + 1) + min);
    	
    	if(diff < 0 && (value + diff) < 0)
    		diff = onlyIncrement ? 0 : value;
    	else if(diff > 0 && (value + diff) > limit)	
    		diff = limit - value;
    	
    	return (value += diff);
	}

}
