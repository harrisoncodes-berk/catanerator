package catanmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CatanMap {

  // Indicates whether the map is extended or not
  private boolean isExtended;
  // Number of tiles in the map
  private int numTiles;
  // Maps resource type to number of tiles
  private Map<String, Integer> resourceTiles;
  // Maps number to number of tiles
  private Map<Integer, Integer> tileNumbers;
  // Maps position to Tile
  private Map<Integer, Tile> tiles;

  public CatanMap(boolean isExtended) {
    this.isExtended = isExtended;
    this.numTiles = setNumTiles(this.isExtended);
    this.resourceTiles = initializeResourceTiles();
    this.tileNumbers = initializeTileNumbers();
    this.tiles = initializeTiles();
  }

  public void showMapInfo() {
    for (Map.Entry<Integer, Tile> entry : tiles.entrySet()) {
      Tile tile = entry.getValue();
      System.out.println(
        "Position: " +
        tile.getPosition() +
        " Resource Type: " +
        tile.getResourceType() +
        " Tile Number: " +
        tile.getNumber()
      );
    }
  }

  private int setNumTiles(boolean isExtended) {
    if (isExtended) {
      return 30;
    } else {
      return 19;
    }
  }

  private Map<String, Integer> initializeResourceTiles() {
    Map<String, Integer> resourceTiles = new HashMap<>();
    if (this.isExtended) {
      resourceTiles.put("wood", 6);
      resourceTiles.put("brick", 5);
      resourceTiles.put("wheat", 6);
      resourceTiles.put("sheep", 6);
      resourceTiles.put("ore", 5);
      resourceTiles.put("desert", 2);
    } else {
      resourceTiles.put("wood", 4);
      resourceTiles.put("brick", 3);
      resourceTiles.put("wheat", 4);
      resourceTiles.put("sheep", 4);
      resourceTiles.put("ore", 3);
      resourceTiles.put("desert", 1);
    }
    return resourceTiles;
  }

  private Map<Integer, Integer> initializeTileNumbers() {
    Map<Integer, Integer> tileNumbers = new HashMap<>();
    if (this.isExtended) {
      tileNumbers.put(2, 2);
      tileNumbers.put(3, 3);
      tileNumbers.put(4, 3);
      tileNumbers.put(5, 3);
      tileNumbers.put(6, 3);
      tileNumbers.put(8, 3);
      tileNumbers.put(9, 3);
      tileNumbers.put(10, 3);
      tileNumbers.put(11, 3);
      tileNumbers.put(12, 2);
    } else {
      tileNumbers.put(2, 1);
      tileNumbers.put(3, 2);
      tileNumbers.put(4, 2);
      tileNumbers.put(5, 2);
      tileNumbers.put(6, 2);
      tileNumbers.put(8, 2);
      tileNumbers.put(9, 2);
      tileNumbers.put(10, 2);
      tileNumbers.put(11, 2);
      tileNumbers.put(12, 1);
    }
    return tileNumbers;
  }

  private String getRandomResourceType() {
    Set<String> resourceSet = resourceTiles.keySet();
    String[] resourceArray = resourceSet.toArray(new String[0]);

    Random rand = new Random();
    int resourceIndex = rand.nextInt(resourceArray.length);

    String resourceType = resourceArray[resourceIndex];
    resourceTiles.put(resourceType, resourceTiles.get(resourceType) - 1);

    if (resourceTiles.get(resourceType) == 0) {
      resourceTiles.remove(resourceType);
    }
    return resourceType;
  }

  private int getRandomTileNumber() {
    Set<Integer> tileNumberSet = tileNumbers.keySet();
    Integer[] tileNumberArray = tileNumberSet.toArray(new Integer[0]);

    Random rand = new Random();
    int numberIndex = rand.nextInt(tileNumberArray.length);

    int tileNumber = tileNumberArray[numberIndex];
    tileNumbers.put(tileNumber, tileNumbers.get(tileNumber) - 1);

    if (tileNumbers.get(tileNumber) == 0) {
      tileNumbers.remove(tileNumber);
    }
    return tileNumber;
  }

  // For each position, generate a random resource, and a random number and create a Tile
  private Map<Integer, Tile> initializeTiles() {
    Map<Integer, Tile> tiles = new HashMap<>();
    int tileNumber = 0;
    for (int i = 0; i < this.numTiles; i++) {
      String resourceType = getRandomResourceType();
      if (!resourceType.equals("desert")) {
        tileNumber = getRandomTileNumber();
      }
      Tile tile = new Tile(resourceType, i, tileNumber);
      tiles.put(i, tile);
    }
    return tiles;
  }
}
