/**
* Random class to produce random numbers
*/
import java.util.Random;
/**
 * Class Carrot that extends an abstract class Plants where it inherits all the attributes and method.
 * Take note that the method inherited, has no body, and thus was override to have a body.
 */
public class Carrot extends Plants{
    public Carrot(Farmer player){
            this.seedName = "Carrot";
            this.cropType = "Root";
            this.waterNeeded = 1;
            this.waterBonusLimit = (super.waterBonusLimit + 2) + player.getWaterBonusIncrease();
            this.fertilizerNeeded = 0;
            this.fertilizerBonusLimit = (super.fertilizerBonusLimit + 1) + player.getFertilizerBonusIncrease();
            this.baseSelling = super.baseSelling + 9;
            this.produceCount = setPlantProduce(1, 2);
            this.xpYield = 7.5f;
            this.harvestTimeInDays = 3;
            this.seedCost = (super.seedCost + 10) + player.getSeedCostReduction();;
            this.witherStatus = false;
            this.produceLowerBound = 1 + player.getBonusPerProduce();;
            this.produceUpperBound = 2;
            this.harvestableStatus = false;
            this.waterAndFertilized = false;
    }
    
    /**
     * Sets the produce counts of the crops based on the min and max.
     * @param min The min represents the minimum produce count.
     * @param max The max represents the maximum produce count.
     * @return the produce count.
     */
    @Override
    public int setPlantProduce(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}