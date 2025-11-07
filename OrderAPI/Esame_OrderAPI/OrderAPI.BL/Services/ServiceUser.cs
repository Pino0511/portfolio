using Esame_OrderAPI.BL.Services.IServices;
using Esame_OrderAPI.DL.Repositories.IRepositories;
using Esame_OrderAPI.Models.Dto;

namespace Esame_OrderAPI.BL.Services;

public class ServiceUser :  IServiceUser
{
    IUserRepository _UserRepository;
    
    public ServiceUser(IUserRepository userRepository) {
        _UserRepository =  userRepository;
    }
    
    public User AddUser(User user)
    {
        _UserRepository.AddUser(user);
        return _UserRepository.GetUserById(user.Id);
    }

    public List<User> getAllUser()
    {
        return _UserRepository.GetUsers();
    }

    public User getUserById(int id)
    {
        return _UserRepository.GetUserById(id);
    }

    public bool RemoveUser(int id)
    {
        return _UserRepository.RemoveUser(id);
    }
}