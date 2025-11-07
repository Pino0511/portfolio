using Esame_OrderAPI.BL.Services.IServices;
using System;

namespace Esame_OrderAPI.BL.Services
{
    public class LoginService : ILoginService
    {
        public string Login(string username, string password)
        {
            // Validazione semplice
            if (string.IsNullOrWhiteSpace(username) || username != "admin" ||
                string.IsNullOrWhiteSpace(password) || password != "123")
            {
                throw new UnauthorizedAccessException("Credenziali errate");
            }

            // Token base64 username:password
            var raw = $"{username}:{password}";
            var bytes = System.Text.Encoding.UTF8.GetBytes(raw);
            return Convert.ToBase64String(bytes);
        }
    }
}
