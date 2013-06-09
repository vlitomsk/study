package ru.nsu.fit.djachenko.pusher.model;

import java.io.*;

public class RecordTable
{
	private static RecordTable instance = new RecordTable();

	public static class Entry
	{
		public final int level;
		public final String name;
		public final long time;
		public final int count;

		Entry(String source)
		{
			String[] splitted = source.split(" ");

			level = Integer.parseInt(splitted[0]);
			name = splitted[1];
			time = Long.parseLong(splitted[2]);
			count = Integer.parseInt(splitted[3]);
		}
	}

	private Entry[] table;
	private String fileName = "records.txt";

	public static RecordTable getInstance()
	{
		return instance;
	}

	private RecordTable()
	{
		{
			File recs = new File(fileName);

			if (!recs.exists())
			{
				try
				{
					recs.createNewFile();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
		{
			int size = Integer.parseInt(reader.readLine());

			table = new Entry[size];

			for (int i = 0; i < size; i++)
			{
				table[i] = new Entry(reader.readLine());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void update(int level, String source)
	{
		table[level] = new Entry(source);

		try (PrintWriter writer = new PrintWriter(new File(fileName)))
		{
			writer.println(table.length);

			for (Entry entry : table)
			{
				writer.println(entry.level + " " + entry.name + " " + entry.time + " " + entry.count);
			}

			writer.flush();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public void update(int level, String name, long time, int count)
	{
		update(level, String.valueOf(level) + " " + name + " " + time + " " + count);
	}

	public void clear(int level)
	{
		update(level, String.valueOf(level) + "  0 0");
	}

	public Entry getEntry(int index)
	{
		return table[index];
	}

	public int size()
	{
		return table.length;
	}
}
