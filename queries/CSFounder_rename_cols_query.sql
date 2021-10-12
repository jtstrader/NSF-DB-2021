/* If importing data directly from excel files, column naming might be
 * incorrect according to Spring convention. Columns must be renamed to 
 * fit the desired form for the Spring application to work 
 */

USE NSF_480
GO
EXEC sp_rename 'CSFounder.MCode', 'm_code', 'COLUMN';
EXEC sp_rename 'CSFounder.Tattoo', 'tattoo', 'COLUMN';
EXEC sp_rename 'CSFounder.BirthSeason', 'birth_season', 'COLUMN';
EXEC sp_rename 'CSFounder.DateOfBirth', 'date_of_birth', 'COLUMN';
