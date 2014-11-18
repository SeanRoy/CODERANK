package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * https://www.codeeval.com/open_challenges/165
 * @author Sean
 *
 */
public class SuggestGroups
{
	public static final HashMap<String,Friend> map = new HashMap<String,Friend>();
	public static final HashMap<String,Integer> hobbyMap = new HashMap<String,Integer>();
	
	public static class Friend
	{
		private String name;
		private List<Friend> friends = new ArrayList<Friend>();
		private List<String> interests = new ArrayList<String>();
		
		public Friend(String name)
		{
			this.name = name;
		}

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public List<Friend> getFriends()
		{
			return friends;
		}

		public void setFriends(List<Friend> friends)
		{
			this.friends = friends;
		}

		public List<String> getInterests()
		{
			return interests;
		}

		public void setHobbies(List<String> interests)
		{
			this.interests = interests;
		}
		
		public void addFriend(Friend friend)
		{
			if ( ! friends.contains(friend) )
			{
				friends.add(friend);
				friend.addFriend(this);
			}
		}
		
		public void addInterest(String interest)
		{
			if ( ! interests.contains(interest) )
			{
				interests.add(interest);
			}
		}
	}
	
	public static Friend getFriend(String name)
	{
		Friend self = map.get(name);
		
		if ( self == null )
		{
			self = new Friend(name);
			
			map.put(name, self);
		}
		
		return self;
	}
	
	public static void mapFriends(String line)
	{
		String [] pieces = line.split(":");
		
		Friend self = getFriend(pieces[0]);
		
		if ( pieces.length > 1 )
		{		
			String [] namesOfFriends = pieces[1].split(",");
			
			for( String name : namesOfFriends )
			{
				self.addFriend(getFriend(name));
			}
					
			if ( pieces.length > 2 )
			{
				String [] interests = pieces[2].split(",");
				
				for( String interest : interests )
				{
					hobbyMap.put(interest, 0);
					self.addInterest(interest);
				}
			}
		}
	}
	
	public static void suggestGroups()
	{
		String [] names = new String[ map.size() ];	
		names = map.keySet().toArray(names);	
		Arrays.sort(names);
			
		for( String n : names )
		{
			StringBuilder sb = new StringBuilder();
			
			Friend f = getFriend(n);
			
			int threshold = (int) Math.ceil(f.getFriends().size() / 2.0);
			
			List<String> groups = new ArrayList<String>();
			
			Object [] hobbyNames = hobbyMap.keySet().toArray();
			for( Object x : hobbyNames )
			{
				hobbyMap.put(x.toString(), 0);
			}
			
			List<Friend> friends = f.getFriends();
			
			for( Friend friend : friends )
			{
				List<String> interests = friend.getInterests();
				
				for( String interest : interests )
				{
					if ( ! f.getInterests().contains(interest) )
					{
						int i = hobbyMap.get(interest) + 1;
						hobbyMap.put(interest, i);
						
						if ( i >= threshold && ! groups.contains(interest) )
						{
							groups.add(interest);
						}
					}
				}
			}
			
			if ( groups.size() > 0 )
			{
				sb.append(n + ":");
				
				Collections.sort(groups);
				
				for( int i = 0; i < groups.size(); i++ )
				{
					sb.append(groups.get(i));
					
					if ( i < groups.size() - 1 )
					{
						sb.append(",");
					}
				}
				
				System.out.println(sb);
			}
		}
	}
	
	public static void main(String [] args)
	{
		if ( args.length >= 1 )
		{
			String fileName = args[0];
			
			BufferedReader bReader = null;
			
			try
			{
				bReader = new BufferedReader(new FileReader(fileName) );
				
				String line = null;
				
				while( ( line = bReader.readLine() ) != null )
				{
					if ( ! line.equals( "" ) )
					{
						String [] pieces = line.split(":");
						
						SuggestGroups.mapFriends(line);
					}
				}
				
				SuggestGroups.suggestGroups();
			}
			catch(FileNotFoundException fnfe)
			{
				System.err.println("File not found: " + fnfe.getMessage());
			}
			catch(IOException ioe)
			{
				System.err.println("Error reading file: " + ioe.getMessage() );
			}
			finally
			{
				try
				{
					bReader.close();
				}
				catch(Exception e){}
			}
		}

	}
}
