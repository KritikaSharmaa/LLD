class NetflixRecommendation {
    public void recommend() {
        System.out.println("Recommending movies based on your viewing history.");
    }
}

// This class violates the Dependency Inversion Principle (DIP) because it directly depends on the concrete implementation of NetflixRecommendation.
// If we want to change the recommendation system in the future, we would have to modify this class, which is not ideal.
public class DIP_bad {
    public static void main(String[] args){
        NetflixRecommendation NetflixRecommendation = new NetflixRecommendation();
        NetflixRecommendation.recommend();
    }
}
