import { UserDTO } from './user.dto';
import { RoleDTO } from './role.dto';
import { ProjectDTO } from './project.dto';

export interface CollaboratorDTO {
  id?: number;
  user: UserDTO;
  role: RoleDTO;
  project: ProjectDTO;
}