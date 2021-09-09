package edu.school21.game.app;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class ArgsProperties {
    @Parameter(names = "--enemiesCount", description = "Number of enemies")
    private Integer enemiesCount;

    @Parameter(names = "--wallsCount", description = "Number of obstacles")
    private Integer wallsCount;

    @Parameter(names = "--size", description = "Field size")
    private Integer sizeMap;

    @Parameter(names = "--profile", description = "Name of property")
    private String profile;

    public Integer getEnemiesCount() {
        return enemiesCount;
    }

    public Integer getWallsCount() {
        return wallsCount;
    }

    public Integer getSizeMap() {
        return sizeMap;
    }

    public String getProfile() {
        return profile;
    }
}
