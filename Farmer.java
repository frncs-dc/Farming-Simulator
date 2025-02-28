/**
 * This class represents the details of the farmer (the player) and.
 * the player themselves. It contains the current coins of the player,
 * their farmer level and experience and, the current day of the game.
 * A Farmer object represent the player and its current
 * attributes. There can only be one player.
 */
public class Farmer {
    protected float objectCoins;
    protected int farmerLevel;
    protected double farmerExperience;
    protected int day;
    protected boolean nextDay;
    protected FarmerState farmerStatus;


    /**
     * Farmer constructor that sets the initial values of the farmer.
     */
    public Farmer() {
        this.objectCoins = 300;
        this.farmerLevel = 0;
        this.farmerExperience = 0;
        this.day = 1;
        this.nextDay = false;
        this.farmerStatus = FarmerState.UNREGISTERED;
    }

    /** Method that upgrades the status of the farmer
     *
     */
    public void registerStatus(){
        FarmerState nextState = this.farmerStatus.upgrade(this);
        if(nextState != this.farmerStatus){
            this.farmerStatus = nextState;
            applyRegistrationFee();
        }
    }

    /** Applies the registration fee from the farmerStatus state
     *
     */
    private void applyRegistrationFee() {
        this.setObjectCoins(this.objectCoins - farmerStatus.getRegistrationFee());
    }

    /** Method that increments the day
     *
     */
    public void nextDay(){
        day++;
    }

    /** A method that computes the total harvest price
     *
     * @param tile is representative of a tile of the board
     * @return final harvest price
     */
    public float computeTotalHarvestPrice(Tile tile){
        float finalHarvestPrice = computeHarvestTotal(tile) + computeWaterBonus(tile) + computeFertilizerBonus(tile);

        if(tile.getCrop().getCropType().equals("Flower")){
            finalHarvestPrice = finalHarvestPrice * 1.1f;
        }

        return finalHarvestPrice;
    }

    /** A method that computes the total harvest value
     *
     * @param tile is representative of a tile of the board
     * @return total harvest value
     */
    public float computeHarvestTotal(Tile tile){
        float harvestTotal = tile.getCrop().getProduceCount() * tile.getCrop().getBaseSelling();

        return harvestTotal;
    }

    /** A method that computes the water bonus
     *
     * @param tile is representative of a tile of the board
     * @return water bonus
     */
    public float computeWaterBonus(Tile tile){
        float waterBonus = 0;

        if(tile.getTimesWatered() > tile.getCrop().getWaterBonusLimit()){
            tile.setTimesWatered(tile.getCrop().getWaterBonusLimit());
        }

        waterBonus = computeHarvestTotal(tile) * 0.2f * (tile.getTimesWatered() - 1);

        return waterBonus;
    }

    /** A method that computes fertilizer bonus
     *
     * @param tile is representative of a tile of the board
     * @return fertilizer bonus
     */
    public float computeFertilizerBonus(Tile tile){
        float fertilizerBonus = 0;

        if(tile.getTimesFertilized() > tile.getCrop().getFertilizerBonusLimit()){
            tile.setTimesFertilized(tile.getCrop().getFertilizerBonusLimit());
        }

        fertilizerBonus = computeHarvestTotal(tile) * 0.5f * (tile.getTimesFertilized());

        return fertilizerBonus;
    }

    /** A method that plants a crop to the tile
     *
     * @param tile is representative of a tile of the board
     * @param plantName name of the crop
     * @param player represents the Farmer
     */
    public void plant(Tile tile, String plantName, Farmer player){
        tile.setCropStatus(true);

        switch (plantName) {
            case "Apple" -> tile.setCrop(new Apple(player));
            case "Mango" -> tile.setCrop(new Mango(player));
            case "Carrot" -> tile.setCrop(new Carrot(player));
            case "Rose" -> tile.setCrop(new Rose(player));
            case "Sunflower" -> tile.setCrop(new Sunflower(player));
            case "Tulips" -> tile.setCrop(new Tulips(player));
            case "Turnip" -> tile.setCrop(new Turnip(player));
            case "Potato" -> tile.setCrop(new Potato(player));
        }
        tile.setDayPlanted(day);
        tile.setDayToHarvest(tile.getDayPlanted() + tile.getCrop().getHarvestTimeInDays());
        objectCoins -= tile.getCrop().getSeedCost();
        System.out.println(objectCoins);
    }

    /** A method that harvests a crop from a tile
     *
     * @param tile is representative of a tile of the board
     */
    public void harvest(Tile tile){
        objectCoins += computeTotalHarvestPrice(tile);
        farmerExperience += tile.getCrop().getXpYield();
        tile.setTimesWatered(0);
        tile.setTimesFertilized(0);
        tile.setCropStatus(false);
        tile.setPlowedStatus(false);
        tile.setCrop(null);
    }

    /** A method that gets the object coins
     *
     * @return object coins of the player
     */
    public float getObjectCoins() {
        return objectCoins;
    }

    /** A method that sets the object coins
     *
     * @param objectCoins of the player
     */
    public void setObjectCoins(float objectCoins) {
        this.objectCoins = objectCoins;
    }

    /** A method that gets the farmer level of the Farmer(player)
     *
     * @return farmer level
     */
    public int getFarmerLevel() {
        return farmerLevel;
    }

    /** A method that sets the farmer level of the Farmer(player)
     *
     * @param farmerLevel represents the level of the farmer
     */
    public void setFarmerLevel(int farmerLevel) {
        this.farmerLevel = farmerLevel;
    }

    /** A method that gets the farmer experience of the Farmer(player)
     *
     * @return farmer experience
     */
    public double getFarmerExperience() {
        return farmerExperience;
    }

    /** A method that sets the farmer experience of the Farmer(player)
     *
     * @param farmerExperience represents the experience of the farmer
     */
    public void setFarmerExperience(double farmerExperience) {
        this.farmerExperience = farmerExperience;
    }

    /** A method that gets the day
     *
     * @return current day
     */
    public int getDay() {
        return day;
    }

    /** A method that sets the day
     *
     * @param day represents the current day
     */
    public void setDay(int day) {
        this.day = day;
    }


    /** A method that sets the next day to true/false 
     *
     * @param nextDay returns if true/false
     */
    public void setNextDay(boolean nextDay) {
        this.nextDay = nextDay;
    }

    /** A method that gets the bonus in produce
     *
     * @return the bonus in produce
     */
    public int getBonusPerProduce() {
        return this.farmerStatus.getBonusPerProduce();
    }

    // /** A method that sets the bonus in produce
    //  *
    //  * @param bonusPerProduce represents the bonus in produce
    //  */
    // public void setBonusPerProduce(int bonusPerProduce) {
    //     this.bonusPerProduce = bonusPerProduce;
    // }

    /** A method that gets the seed cost reduction
     *
     * @return the seed cost reduction
     */
    public int getSeedCostReduction() {
        return this.farmerStatus.getSeedCostReduction();
    }

    // /** A method that sets the seed cost reduction
    //  *
    //  * @param seedCostReduction represents the reduction in seed cost
    //  */
    // public void setSeedCostReduction(int seedCostReduction) {
    //     this.seedCostReduction = seedCostReduction;
    // }

    /** A method that gets the water bonus increase
     *
     * @return the increase in the water bonus limit
     */
    public int getWaterBonusIncrease() {
        return this.farmerStatus.getWaterBonusIncrease();
    }

    // /** A method that sets the water bonus increase
    //  *
    //  * @param waterBonusIncrease the increase in the water bonus limit
    //  */
    // public void setWaterBonusIncrease(int waterBonusIncrease) {
    //     this.waterBonusIncrease = waterBonusIncrease;
    // }

    /** A method that gets the fertilizer bonus increase
     *
     * @return the increase in the fertilizer bonus limit
     */
    public int getFertilizerBonusIncrease() {
        return this.farmerStatus.getFertilizerBonusIncrease();
    }

    // /** A method that sets the fertilizer bonus increase
    //  *
    //  * @param fertilizerBonusIncrease represents the increase in the fertilizer bonus limit
    //  */
    // public void setFertilizerBonusIncrease(int fertilizerBonusIncrease) {
    //     this.fertilizerBonusIncrease = fertilizerBonusIncrease;
    // }

    public FarmerState getFarmerStatus() {
        return farmerStatus;
    }
}