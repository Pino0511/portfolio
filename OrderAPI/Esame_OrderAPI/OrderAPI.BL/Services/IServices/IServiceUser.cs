using Esame_OrderAPI.Models.Dto;

namespace Esame_OrderAPI.BL.Services.IServices;

public interface IServiceUser
{
    public User AddUser(User user);
    
    public  List<User> getAllUser();
    
    public User getUserById(int id);
    
    public bool RemoveUser(int id);
}