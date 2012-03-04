package dao;

import java.util.List;

import model.Status;

public interface StatusDao
{
	public int insertStatus(Status status);

	public List<Status> listAll();
}
