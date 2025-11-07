using Esame_OrderAPI.Models.Enum;
using System.ComponentModel.DataAnnotations;

namespace Esame_OrderAPI.Models.Dto
{
    public class User
    {
        public int Id { get; set; }
        public required string Name { get; set; }
        public required string Password { get; set; }
        public RoleEnum Role { get; set; }
    }
}
