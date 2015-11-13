package factory;

import java.io.IOException;

import dao.*;

public abstract class Factory {
	public abstract StudDao getStudDao() throws IOException, ClassNotFoundException;
	public abstract GroupDao getGroupDao();
}
