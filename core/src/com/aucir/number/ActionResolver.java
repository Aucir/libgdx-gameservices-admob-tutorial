package com.aucir.number;

public interface ActionResolver {
	public boolean getSignedInGPGS();
	public void loginGPGS();
	public void submitScoreGPGS(int score,String id);
	public void unlockAchievementGPGS(String achievementId);
	public void getLeaderboardGPGS(String id);
	public void getAchievementsGPGS();
}
