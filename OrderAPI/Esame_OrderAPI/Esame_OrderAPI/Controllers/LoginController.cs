using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Esame_OrderAPI.BL.Services.IServices;
using Esame_OrderAPI.Models.Dto;

namespace Esame_OrderAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class LoginController : ControllerBase
    {
        private readonly ILoginService _loginService;

        public LoginController(ILoginService loginService)
        {
            _loginService = loginService;
        }

        [HttpPost("/api/auth/login")]
        [AllowAnonymous]
        public IActionResult Login([FromBody] LoginRequest loginRequest)
        {
            try
            {
                var token = _loginService.Login(loginRequest.Name, loginRequest.Password);
                return Ok(token);
            }
            catch (UnauthorizedAccessException)
            {
                return Unauthorized("Credenziali errate");
            }
        }
    }
}
