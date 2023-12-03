package bunchbysoh;

public class Main {
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();

    //Taking rated capacity of the battery as 120
    int ratedCapacity = 120;

    for(int presentCapacity : presentCapacities){
      //Throwing exception if present capacity is negative
      if(presentCapacity < 0 || presentCapacity > 120){
        throw new IllegalArgumentException("Invalid present capacity");
      }

      //Finding SoH percentage
      double soh = (double)(presentCapacity / ratedCapacity) * 100;

      //Classify batteries by SoH
      if(soh >= 80 && soh <= 100){
        counts.healthy++;
      }else if(soh >= 62 && soh < 80){
        counts.exchange++;
      }else{
        counts.failed++;
      }
    }
    //Return the result
    return counts;
  }

  static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");

    //Test case : Present battery capacities
    int[] presentCapacities = {113, 116, 80, 95, 92, 70};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);

    //Validating counts returned by countBatteriesByHealth method
    assert(counts.healthy == 2);
    assert(counts.exchange == 3);
    assert(counts.failed == 1);

    //Checking for other test cases
    int[] testCase1 = {0}; //Battery with 0 present capacity 
    CountsBySoH counts = countBatteriesByHealth(testCase1);

    //Validating counts returned by countBatteriesByHealth method
    assert(counts.healthy == 0);
    assert(counts.exchange == 0);
    assert(counts.failed == 1);

    int[] testCase2 = {120}; //Battery with full rated capacity 
    CountsBySoH counts = countBatteriesByHealth(testCase2);

    //Validating counts returned by countBatteriesByHealth method
    assert(counts.healthy == 1);
    assert(counts.exchange == 0);
    assert(counts.failed == 0);

    int[] testCase3 = {-40, 90, 130}; //Battery with invalid present capacities
    try{
      CountsBySoH counts = countBatteriesByHealth(testCase3);
    }catch(IllegalArgumentException e){
      //Display error message
      System.out.println(e.getMessage());
    }
    
    System.out.println("Done counting :)\n");
  }

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}
