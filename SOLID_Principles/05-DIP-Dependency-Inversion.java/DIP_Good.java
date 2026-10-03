interface RecommendationService {
    void recommend();
}

class recentlyWatchedRecommendation implements RecommendationService {
    @Override
    public void recommend() {
        System.out.println("Recommending movies based on your recently watched history.");
    }
}

class recentlyAddedRecommendation implements RecommendationService {
    @Override
    public void recommend() {
        System.out.println("Recommending movies based on your recently added history.");
    }
}

// This class adheres to the Dependency Inversion Principle (DIP) because it depends on the abstraction (RecommendationService) rather than a concrete implementation.
class NetflixRecommendation {
    private RecommendationService recommendationService;

    public NetflixRecommendation(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    public void recommend() {
        recommendationService.recommend();
    }
}

public class DIP_Good {
    public static void main(String[] args){
        NetflixRecommendation netflixRecommendation1 = new NetflixRecommendation(new recentlyAddedRecommendation());
        netflixRecommendation1.recommend();
    }
}
