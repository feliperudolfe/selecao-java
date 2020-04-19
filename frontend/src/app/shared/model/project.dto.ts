import { UserDTO } from './user.dto';
import { CollaboratorDTO } from './collaborator.dto';

export interface ProjectDTO {
  id?: number;
  name: string;
  image?: string;
  startDate: Date;
  registrationDate?: Date;
  token?: string;
  reportExecution?: boolean;
  owner?: UserDTO;
  contributors?: Array<CollaboratorDTO>;
}