using Microsoft.PowerBI.Api;
using Esame_OrderAPI.Models.Constant;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Esame_OrderAPI.Models.Enum
{
    public enum RoleEnum
    {
        [Description(RoleConstant.Admin)]
        Admin = 0,
        [Description(RoleConstant.User)]
        User = 1
    }
}
