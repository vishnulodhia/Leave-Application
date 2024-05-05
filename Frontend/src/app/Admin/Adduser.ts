export class Adduser{
    username!:String
    email!:String
    password!:String
    roles!:object

    constructor (name:String,email:String,password:String,roleid:String){
     this.username = name;
     this.email = email;
     this.password= password;
     this.roles= {
        role_id:roleid,
     };
    }
     
    
    
    }