using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Esame_OrderAPI.BL.Services.IServices;
using Esame_OrderAPI.Models.Dto;
using System.Collections.Generic;

namespace Esame_OrderAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserController : ControllerBase
    {
        private readonly IServiceUser _userService;

        public UserController(IServiceUser userService)
        {
            _userService = userService;
        }

        [HttpGet("/api/users")]
        [AllowAnonymous]
        public List<User> GetAllUsers()
        {
            return _userService.getAllUser();
        }

        [HttpGet("/api/users/{id}")]
        [AllowAnonymous]
        public User GetUserById([FromRoute] int id)
        {
            return _userService.getUserById(id);
        }

        [HttpPost("/api/users")]
        public User AddUser([FromBody] User user)
        {
            return _userService.AddUser(user);
        }

        [HttpDelete("/api/users/{id}")]
        public bool DeleteUser(int id)
        {
            return _userService.RemoveUser(id);
        }
    }
}
