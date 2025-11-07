
using Microsoft.Extensions.Options;
using Esame_OrderAPI.DL.Repositories.IRepositories;
using Esame_OrderAPI.Models.Configurations;
using Esame_OrderAPI.Models.Dto;
using Newtonsoft.Json;

namespace Esame_OrderAPI.DL.Repositories;

public class UserRepository : IUserRepository
{
    FileConfiguration _fileConfiguration;

    public UserRepository(IOptions<FileConfiguration> fileConfiguration)
    {
        _fileConfiguration = fileConfiguration.Value;
    }
    
    static List<User> Users = new List<User>();
    
    public User AddUser(User user)
    {
        CaricaUserDaFile(_fileConfiguration.UsersFile);
        if (user == null)
        {
            throw new ArgumentNullException(nameof(user), "User non valido!");
        }
        
        user.Id = Users.Any() ? Users.Max(u => u.Id) +  1 : 1;
        Users.Add(user);
        SalvaUserSuFile(_fileConfiguration.UsersFile);
        return user;
    }

    public bool ExistUser(int id)
    {
        CaricaUserDaFile(_fileConfiguration.UsersFile);
        return Users.Any(u => u.Id == id);
    }

    public bool RemoveUser(int id)
    {
        if (ExistUser(id)) 
        {
        CaricaUserDaFile(_fileConfiguration.UsersFile);
        Users.Remove(Users.First(u => u.Id == id));
        SalvaUserSuFile(_fileConfiguration.UsersFile);
        return true;
        }
        return false;
    }

    public User UpdateUser(User user, int id)
    {
        CaricaUserDaFile(_fileConfiguration.UsersFile);
        if (user == null)
        {
            throw new ArgumentNullException(nameof(user), "User non valido!");
        }

        if (ExistUser(user.Id))
        {
            User previusUser = Users.FirstOrDefault(u => u.Id == user.Id);
            previusUser.Name = user.Name;
        }

        SalvaUserSuFile(_fileConfiguration.UsersFile);
        return GetUserById(user.Id);
    }

    public List<User> GetUsers()
    {
        CaricaUserDaFile(_fileConfiguration.UsersFile);
        return Users;
    }

    public User GetUserById(int id)
    {
        CaricaUserDaFile(_fileConfiguration.UsersFile);
        return Users.FirstOrDefault(u => u.Id == id);
    }
    
    static void CaricaUserDaFile(string percorsoFile)
    {
        if (File.Exists(percorsoFile))
        {
            try
            {
                string json = File.ReadAllText(percorsoFile);
                Users = JsonConvert.DeserializeObject<List<User>>(json) ?? new List<User>();
                Console.WriteLine("User caricati con successo!");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Errore durante il caricamento degli user: {ex.Message}");
            }
        }
        else
        {
            Console.WriteLine("File user non trovato, nessun user caricato.");
        }
    }
    
    static void SalvaUserSuFile(string percorsoFile)
    {
        try
        {
            string json = JsonConvert.SerializeObject(Users, Formatting.Indented);
            File.WriteAllText(percorsoFile, json);
            Console.WriteLine("Users salvati con successo!");
            CaricaUserDaFile(percorsoFile);
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Errore durante il salvataggio degli user: {ex.Message}");
        }
    }
}