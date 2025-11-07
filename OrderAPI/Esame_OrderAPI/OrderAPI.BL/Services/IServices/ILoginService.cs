using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Esame_OrderAPI.BL.Services.IServices
{
    public interface ILoginService
    {
        string Login(string username, string password);
        
        // string Register(string username, string password);
    }
}