/* If importing data directly from excel files, column naming might be
 * incorrect according to Spring convention. Columns must be renamed to 
 * fit the desired form for the Spring application to work 
 */

USE NSF_480
GO
EXEC sp_rename 'CSSubject.SequenceNo', 'sequence_no', 'COLUMN';
EXEC sp_rename 'CSSubject.AnimalId', 'animal_id', 'COLUMN';
EXEC sp_rename 'CSSubject.DateOfBirth', 'date_of_birth', 'COLUMN';
EXEC sp_rename 'CSSubject.MonthDOB', 'month_dob', 'COLUMN';
EXEC sp_rename 'CSSubject.YearDOB', 'year_dob', 'COLUMN';
EXEC sp_rename 'CSSubject.Sex', 'sex', 'COLUMN';
EXEC sp_rename 'CSSubject.BirthGroup', 'birth_group', 'COLUMN';
EXEC sp_rename 'CSSubject.BehaviorMom', 'behavior_mom', 'COLUMN';
EXEC sp_rename 'CSSubject.CurrentGroup', 'current_group', 'COLUMN';
EXEC sp_rename 'CSSubject.BirthSeason', 'birth_season', 'COLUMN';
EXEC sp_rename 'CSSubject.Status', 'status', 'COLUMN';
EXEC sp_rename 'CSSubject.DateOfDeath', 'date_of_death', 'COLUMN';
EXEC sp_rename 'CSSubject.DateOfRemove', 'date_of_remove', 'COLUMN';
EXEC sp_rename 'CSSubject.AgeOfDelivery', 'age_of_delivery', 'COLUMN';
EXEC sp_rename 'CSSubject.MomDOB', 'mom_dob', 'COLUMN';
EXEC sp_rename 'CSSubject.AgeOfRemove', 'age_of_remove', 'COLUMN';
EXEC sp_rename 'CSSubject.AgeOfDOD', 'age_of_death', 'COLUMN';
EXEC sp_rename 'CSSubject.SireGenetic', 'sire_genetic', 'COLUMN';
EXEC sp_rename 'CSSubject.DamGenetic', 'dam_genetic', 'COLUMN';
