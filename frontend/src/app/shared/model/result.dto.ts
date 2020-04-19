import { UserDTO } from './user.dto';
import { CollaboratorDTO } from './collaborator.dto';

export interface ResultDTO {

  id: number;
  increment: number;
  suiteClass: string;
  testClass: string;
  suiteId: string;
  suiteName: string;
  suiteDescription: string;
  suiteSqlPath: Array<string>;
  testId: string;
  testName: string;
  testDescription: string;
  datasetId: string;
  csvPath: string;
  sqlPath: Array<string>;
  headerDataset: string;
  lineDataset: string;
  start: Date;
  end: Date;
  executionTime: number;
  evaluation: string;
  relevance: string;
  criticity: string;
  motive: string;
  screenBase64: string;
  videoBase64: string;
  preconditions: Array<string>;
  procedures: Array<string>;
  postconditions: Array<string>;
  stepsPreconditions: Array<string>;
  stepsProcedures: Array<string>;
  stepsPostconditions: Array<string>;
  browserName: string;
  browserVersion: string;
  so: string;
  projectVersion: string;

}