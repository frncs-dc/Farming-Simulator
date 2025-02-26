public enum FarmerState {
    UNREGISTERED(0, 0, 0, 0, 0, 0) {
        @Override
        public FarmerState upgrade(Farmer farmer) {
            if (farmer.getFarmerLevel() >= REGISTERED.getLevelRequirement()) {
                return REGISTERED;
            }
            return this;
        }
    },
    
    REGISTERED(1, 1, -1, 0, 0, 200) {
        @Override
        public FarmerState upgrade(Farmer farmer) {
            if (farmer.getFarmerLevel() >= DISTINGUISHED.getLevelRequirement()) {
                return DISTINGUISHED;
            }
            return this;
        }
    },

    DISTINGUISHED(10, 2, -2, 1, 0, 300) {
        @Override
        public FarmerState upgrade(Farmer farmer) {
            if (farmer.getFarmerLevel() >= LEGENDARY.getLevelRequirement()) {
                return LEGENDARY;
            }
            return this;
        }
    },

    LEGENDARY(15, 4, -3, 2, 1, 400) {
        @Override
        public FarmerState upgrade(Farmer farmer) {
            return this;
        }
    };

    private final int levelRequirement;
    private final int bonusPerProduce;
    private final int seedCostReduction;
    private final int waterBonusIncrease;
    private final int fertilizerBonusIncrease;
    private final int registrationFee;

    FarmerState(int levelRequirement, int bonusPerProduce, int seedCostReduction,
                int waterBonusIncrease, int fertilizerBonusIncrease, int registrationFee) {
        this.levelRequirement = levelRequirement;
        this.bonusPerProduce = bonusPerProduce;
        this.seedCostReduction = seedCostReduction;
        this.waterBonusIncrease = waterBonusIncrease;
        this.fertilizerBonusIncrease = fertilizerBonusIncrease;
        this.registrationFee = registrationFee;
    }

    // Getters for state-specific properties
    public int getLevelRequirement() {
        return levelRequirement;
    }

    public int getBonusPerProduce() {
        return bonusPerProduce;
    }

    public int getSeedCostReduction() {
        return seedCostReduction;
    }

    public int getWaterBonusIncrease() {
        return waterBonusIncrease;
    }

    public int getFertilizerBonusIncrease() {
        return fertilizerBonusIncrease;
    }

    public int getRegistrationFee() {
        return registrationFee;
    }

    // Abstract method to be implemented by each enum constant
    public abstract FarmerState upgrade(Farmer farmer);
}