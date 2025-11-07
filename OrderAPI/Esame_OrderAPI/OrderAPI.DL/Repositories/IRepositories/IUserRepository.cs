using Esame_OrderAPI.Models.Dto;

namespace Esame_OrderAPI.DL.Repositories.IRepositories;

public interface IUserRepository
{
    public User AddUser(User user);
    
    public bool ExistUser(int id);
    public bool RemoveUser(int id);
    
    public User UpdateUser(User user, int id);
    
    public List<User> GetUsers();
    
    public User GetUserById(int id);
}