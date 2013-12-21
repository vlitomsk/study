package ru.nsu.fit.g1201.races.model;

public class MapList
{
	private final static MapList instance = new MapList();
	private final String[] mapNames = new String[]
			{
					"Random"
			};

	private MapList()
	{}

	public static MapList getInstance()
	{
		return instance;
	}

	public String[] getMapNames()
	{
		return mapNames;
	}

	public RoadMap getRandomRoadMap(Race race)
	{
		return new RandomRoadMap(race);
	}

	public RoadMap getRoadMap(int index, Race race)
	{
		switch (index)
		{
			case 0:
				return getRandomRoadMap(race);
			default:
				return null;
		}
	}
}
