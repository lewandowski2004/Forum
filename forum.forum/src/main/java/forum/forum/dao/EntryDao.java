package forum.forum.dao;

import forum.forum.dto.Entry;


public interface EntryDao {

	public boolean addEntry(Entry e);
	public Entry getEntry(int id);

}
